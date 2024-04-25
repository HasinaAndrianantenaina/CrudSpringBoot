package com.java.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.springboot.Bean.EmployeeBean;
import com.java.springboot.model.Commune;
import com.java.springboot.model.Employee;
import com.java.springboot.repository.CommuneRepository;
import com.java.springboot.repository.EmployeeRepository;
import com.java.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CommuneRepository communeRepository;

	@Override
	public List<EmployeeBean> listAll() {
		List<Employee> listEntity = this.employeeRepository.getAllEmployee();
		List<EmployeeBean> listBean = new ArrayList();

		for (Employee employeeEntity : listEntity) {
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setIdEmployee(employeeEntity.getId());
			employeeBean.setNomEmployee(employeeEntity.getFirstname());
			employeeBean.setPrenomEmployee(employeeEntity.getLastname());
			employeeBean.setGender(employeeEntity.getGender());
			String nomCommune = this.communeRepository.getNomCommune(employeeEntity.getCommune());
			//employeeBean.setNomCommune(employeeEntity.getCommune().getNom());
			employeeBean.setNomCommune(nomCommune);

			if (employeeEntity.getGender() == true) {
				employeeBean.setsGender("masculin");
			} else if (employeeEntity.getGender() == false) {
				employeeBean.setsGender("féminin");
			}

			listBean.add(employeeBean);
		}
		return listBean;
	}

	@Override
	public void create(EmployeeBean employeeBean) {
		Employee employeeEntity = new Employee();
		employeeEntity.setFirstname(employeeBean.getNomEmployee());
		employeeEntity.setLastname(employeeBean.getPrenomEmployee());
		employeeEntity.setGender(employeeBean.getGender());

		Optional<Commune> communeOpt = communeRepository.getCommuneById(employeeBean.getCommuneId());
		employeeEntity.setCommune(communeOpt.get());

		employeeRepository.save(employeeEntity);

	}

	@Override
	public void update(EmployeeBean employeeBean) {
		Employee employeeEntity = employeeRepository.getEmployeeById(employeeBean.getIdEmployee());
		employeeEntity.setFirstname(employeeBean.getNomEmployee());
		employeeEntity.setLastname(employeeBean.getPrenomEmployee());
		employeeEntity.setGender(employeeBean.getGender());
		Optional<Commune> communeOpt = communeRepository.getCommuneById(employeeBean.getCommuneId());
		employeeEntity.setCommune(communeOpt.get());

		employeeRepository.save(employeeEntity);
	}

	/*
	 * @Override public EmployeeBean updateid(Long id) {
	 * 
	 * return employeeRepository.findById(id).get(); }
	 */
	@Override
	public EmployeeBean getEmployeById(Long id) {
		EmployeeBean employeeBean = new EmployeeBean();
		Employee employeeEntity = employeeRepository.getEmployeeById(id);
		employeeBean.setIdEmployee(employeeEntity.getId());
		employeeBean.setNomEmployee(employeeEntity.getFirstname());
		employeeBean.setGender(employeeEntity.getGender());
		employeeBean.setPrenomEmployee(employeeEntity.getLastname());
		Optional<Commune> commueOpt = communeRepository.getCommuneById(employeeEntity.getCommune().getId());

		String idCommune = String.valueOf(commueOpt.get().getId());

		employeeBean.setCommuneId(Integer.valueOf(idCommune));
		employeeBean.setNomCommune(commueOpt.get().getNom());
		return employeeBean;
	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);

	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return this.employeeRepository.findAll(pageable);
	}

}
