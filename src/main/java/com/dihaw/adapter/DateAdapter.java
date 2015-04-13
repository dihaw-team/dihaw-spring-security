package com.dihaw.adapter;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	@Override
	public Date unmarshal(String v) throws Exception {
		return new Date(Long.parseLong(v));
	}

	@Override
	public String marshal(Date v) throws Exception {
		return String.valueOf(v.getTime());
	}

}
