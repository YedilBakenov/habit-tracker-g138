package ru.habbit.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.habbit.tracker.dto.UserDto;
import ru.habbit.tracker.model.User;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mapping( source = "createdAt", target = "create")
    @Mapping( source = "updatedAt", target = "update")
    UserDto toDto(User user);

    @Mapping( source = "create", target = "createdAt")
    @Mapping(source = "update", target = "updatedAt")
    User toModel(UserDto userDto);

    List<UserDto> toListDto(List<User> users);
    List<User>toListModel(List<UserDto> userDtos);
}
