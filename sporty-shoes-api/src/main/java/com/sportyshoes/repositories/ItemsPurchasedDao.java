package com.sportyshoes.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportyshoes.entities.ItemsPurchased;
import com.sportyshoes.entities.enumerations.BrandEnum;
import com.sportyshoes.entities.enumerations.ColorEnum;
import com.sportyshoes.entities.enumerations.GenderEnum;
import com.sportyshoes.entities.enumerations.SizeEnum;
import com.sportyshoes.entities.enumerations.SportEnum;
import com.sportyshoes.entities.enumerations.TerrainEnum;
import com.sportyshoes.entities.enumerations.TypeEnum;

@Repository
public class ItemsPurchasedDao {
	
	@Autowired
	private EntityManager em;

	public List<ItemsPurchased> searchByDatesAndCategory(LocalDateTime date1, LocalDateTime date2, String category) {
		final CriteriaBuilder cb=em.getCriteriaBuilder();
		final CriteriaQuery<ItemsPurchased> cq=cb.createQuery(ItemsPurchased.class);
		final Root<ItemsPurchased> root=cq.from(ItemsPurchased.class);
		final List<Predicate> where=new ArrayList<>();
		/*critere zone*/
		
		Optional.ofNullable(date1)
		.ifPresent(name->{
			where
			.add(cb.greaterThanOrEqualTo(root.get("purchasedDate"),date1 ));
		});
		Optional.ofNullable(date2)
		.ifPresent(name->{
			where
			.add(cb.lessThanOrEqualTo(root.get("purchasedDate"),date2 ));
		});
		Optional.ofNullable(category)
		.ifPresent(color->where.add(cb.equal(root.get("color"), ColorEnum.valueOf(color))));
		
		Optional.ofNullable(category)
		.ifPresent(brand->where.add(cb.equal(root.get("brand"), BrandEnum.valueOf(brand))));
		
		Optional.ofNullable(category)
		.ifPresent(gender->where.add(cb.equal(root.get("gender"), GenderEnum.valueOf(gender))));
		
		Optional.ofNullable(category)
		.ifPresent(size->where.add(cb.equal(root.get("size"), SizeEnum.valueOf(size))));
		
		Optional.ofNullable(category)
		.ifPresent(sport->where.add(cb.equal(root.get("sport"), SportEnum.valueOf(sport))));
		
		Optional.ofNullable(category)
		.ifPresent(terrain->where.add(cb.equal(root.get("terrain"), TerrainEnum.valueOf(terrain))));
		
		Optional.ofNullable(category)
		.ifPresent(type->where.add(cb.equal(root.get("type"), TypeEnum.valueOf(type))));
		/*end critere zone*/
		cq.select(root).where(where.stream().toArray(Predicate[]::new));
		final TypedQuery<ItemsPurchased> typedQuery=em.createQuery(cq);
		return typedQuery.getResultList();
	}
}
