package com.hibernate.repository;

import java.util.List;

import com.hibernate.entity.Owner;

public interface OwnerRepository {
	List<Owner> findSelectedOwnersWithoutHql(List<Integer> ownerIdList);

	Owner findOwnerWithoutHql(Integer ownerId);

	List<Owner> findSelectedOwnersWithHql(List<Integer> ownerIdList);

	Owner findOwnerWithHql(Integer ownerId);
}
