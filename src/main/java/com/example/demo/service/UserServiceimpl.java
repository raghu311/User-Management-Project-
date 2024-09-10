package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginFormDTO;
import com.example.demo.dto.RegisterFormDTO;
import com.example.demo.dto.ResetPwdFormDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.CityEntity;
import com.example.demo.entity.CountryEntity;
import com.example.demo.entity.StateEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.CityRepo;
import com.example.demo.repo.CountryRepo;
import com.example.demo.repo.StateRepo;
import com.example.demo.repo.UserRepo;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> countriesList = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		countriesList.forEach(c -> {
			countryMap.put(c.getCountryId(), c.getCountryName());

		});

		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> stateList = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		stateList.forEach(s -> {
			stateMap.put(s.getStateId(), s.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> cityList = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cityList.forEach(c -> {
			cityMap.put(c.getCityId(), c.getCityName());

		});

		return cityMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		UserEntity byEmail = userRepo.findByEmail(email);
		if (byEmail != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveUser(RegisterFormDTO registerFormDTO) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(registerFormDTO, userEntity);

		CountryEntity country = countryRepo.findById(registerFormDTO.getCountryId()).orElse(null);
		userEntity.setCountry(country);

		StateEntity state = stateRepo.findById(registerFormDTO.getStateId()).orElse(null);
		userEntity.setState(state);

		CityEntity city = cityRepo.findById(registerFormDTO.getCityId()).orElse(null);
		userEntity.setCity(city);

		String randomPwd = generateRandomPwd();

		userEntity.setPwd(randomPwd);
		userEntity.setPwdUpdated("No");

		UserEntity savedUser = userRepo.save(userEntity);

		if (null != savedUser.getUserId()) {
			String subject = "Your Account created";
			String body = "Your Password to Login::::" + randomPwd;
			String to = registerFormDTO.getEmail();
			emailService.sendEmail(subject, body, to);

			return true;

		}

		return false;
	}

	@Override
	public UserDTO login(LoginFormDTO loginFormDTO) {

		UserEntity userEntity = userRepo.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPwd());
		if (userEntity != null) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(userEntity, userDTO);
			return userDTO;

		}
		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdFormDTO resetPwdDTO) {

		String email = resetPwdDTO.getEmail();
		UserEntity entity = userRepo.findByEmail(email);
		entity.setPwd(resetPwdDTO.getNewPwd());
		entity.setPwdUpdated("YES");
		userRepo.save(entity);
		return false;
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		
		
		return null;
	}

	private String generateRandomPwd() {

		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";

		String alphbates = upperCaseLetters + lowerCaseLetters;

		Random random = new Random();

		StringBuffer generatedPwd = new StringBuffer();

		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(alphbates.length());
			generatedPwd.append(alphbates.charAt(randomIndex));
		}
		return generatedPwd.toString();
	}
}
