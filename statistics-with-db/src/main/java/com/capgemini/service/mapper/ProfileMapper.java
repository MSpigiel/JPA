package com.capgemini.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.dataaccess.entities.ProfileEntity;
import com.capgemini.service.to.ProfileTO;

public class ProfileMapper {
	public static ProfileTO map(ProfileEntity profileEntity) {
		if (profileEntity != null) {
			ProfileTO ProfileTO = new ProfileTO();
			ProfileTO.setAboutMe(profileEntity.getAboutMe());
			ProfileTO.setId(profileEntity.getId());
			ProfileTO.setLifeMotto(profileEntity.getLifeMotto());
			ProfileTO.setName(profileEntity.getName());
			ProfileTO.setSurname(profileEntity.getSurname());
			return ProfileTO;
		}
		return null;
	}

	public static ProfileEntity map(ProfileTO ProfileTO) {
		if (ProfileTO != null) {
			ProfileEntity profileEntity = new ProfileEntity();
			profileEntity.setAboutMe(ProfileTO.getAboutMe());
			profileEntity.setId(ProfileTO.getId());
			profileEntity.setLifeMotto(ProfileTO.getLifeMotto());
			profileEntity.setName(ProfileTO.getName());
			profileEntity.setSurname(ProfileTO.getSurname());
			return profileEntity;
		}
		return null;
	}

	public static List<ProfileTO> map2TOs(List<ProfileEntity> profileEntities) {
		return profileEntities.stream().map(ProfileMapper::map).collect(Collectors.toList());
	}

	public static List<ProfileEntity> map2Entities(List<ProfileTO> ProfileTOs) {
		return ProfileTOs.stream().map(ProfileMapper::map).collect(Collectors.toList());
	}
}
