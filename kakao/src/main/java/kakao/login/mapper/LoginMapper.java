package kakao.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import kakao.users.dto.UsersDto;

@Mapper
public interface LoginMapper {
	
	UsersDto selectUserId(String userId) throws Exception;
	
}