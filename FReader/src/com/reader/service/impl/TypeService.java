package com.reader.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reader.model.Type;
import com.reader.service.interfaces.ITypeService;

@Service
public class TypeService implements ITypeService {

	public List<Type> getTypes() {
		return Type.me.getAllTypes();
	}

	public boolean delType(int id) {
		return Type.me.deleteById(id);
	}

	public boolean updateType(Type type) {
		return type.update();
	}

	public boolean saveType(Type type) {
		if(Type.me.find("select * from type where name = '" + type.getStr("name") + "'").size() == 0)
			return type.save();
		else
			return false;
	}

}
