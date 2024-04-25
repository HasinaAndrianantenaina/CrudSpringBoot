package com.java.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springboot.Bean.CommuneBean;
import com.java.springboot.model.Commune;
import com.java.springboot.repository.CommuneRepository;
import com.java.springboot.service.CommuneService;

@Service
public class CommuneServiceImpl implements CommuneService {

	@Autowired
	CommuneRepository communeRepository;

	@Override
	public List<CommuneBean> getCommune() {
		List<Commune> listEntity = communeRepository.getCommune();
		List<CommuneBean> listBean = new ArrayList<CommuneBean>();
		for (Commune communeEntity : listEntity) {
			CommuneBean communeBean = new CommuneBean();
			communeBean.setIdCommune(communeEntity.getId());
			communeBean.setNomCommune(communeEntity.getNom());
			listBean.add(communeBean);
		}

		return listBean;
	}

}
