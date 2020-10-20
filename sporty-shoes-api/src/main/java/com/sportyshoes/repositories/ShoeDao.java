package com.sportyshoes.repositories;

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

import com.sportyshoes.entities.Shoe;
import com.sportyshoes.entities.ShoesFilter;
import com.sportyshoes.entities.enumerations.BrandEnum;
import com.sportyshoes.entities.enumerations.ColorEnum;
import com.sportyshoes.entities.enumerations.GenderEnum;
import com.sportyshoes.entities.enumerations.SizeEnum;
import com.sportyshoes.entities.enumerations.SportEnum;
import com.sportyshoes.entities.enumerations.TerrainEnum;
import com.sportyshoes.entities.enumerations.TypeEnum;

@Repository
public class ShoeDao {
	
	@Autowired
	private EntityManager em;

	public List<Shoe> advancedShoesResearch(ShoesFilter filter) {
		final CriteriaBuilder cb=em.getCriteriaBuilder();
		final CriteriaQuery<Shoe> cq=cb.createQuery(Shoe.class);
		final Root<Shoe> root=cq.from(Shoe.class);
		final List<Predicate> where=new ArrayList<>();
		/*critere zone*/
		
		Optional.ofNullable(filter.getName())
		.ifPresent(name->{
			where
			.add(cb.like(root
					.get("name"), "%"+name+"%"));
		});
		
		Optional.ofNullable(filter.getColor())
		.ifPresent(color->where.add(cb.equal(root.get("color"), ColorEnum.valueOf(color))));
		
		Optional.ofNullable(filter.getBrand())
		.ifPresent(brand->where.add(cb.equal(root.get("brand"), BrandEnum.valueOf(brand))));
		
		Optional.ofNullable(filter.getGender())
		.ifPresent(gender->where.add(cb.equal(root.get("gender"), GenderEnum.valueOf(gender))));
		
		Optional.ofNullable(filter.getSize())
		.ifPresent(size->where.add(cb.equal(root.get("size"), SizeEnum.valueOf(size))));
		
		Optional.ofNullable(filter.getSport())
		.ifPresent(sport->where.add(cb.equal(root.get("sport"), SportEnum.valueOf(sport))));
		
		Optional.ofNullable(filter.getTerrain())
		.ifPresent(terrain->where.add(cb.equal(root.get("terrain"), TerrainEnum.valueOf(terrain))));
		
		Optional.ofNullable(filter.getType())
		.ifPresent(type->where.add(cb.equal(root.get("type"), TypeEnum.valueOf(type))));
		/*end critere zone*/
		cq.select(root).where(where.stream().toArray(Predicate[]::new));
		final TypedQuery<Shoe> typedQuery=em.createQuery(cq);
		return typedQuery.getResultList();
	}
}
