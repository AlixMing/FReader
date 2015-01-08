package com.reader.service.interfaces;

import java.util.List;

import com.reader.model.Type;

public interface ITypeService {
	List<Type> getTypes();
	boolean delType(int id);
	boolean updateType(Type type);
	boolean saveType(Type type);
}
