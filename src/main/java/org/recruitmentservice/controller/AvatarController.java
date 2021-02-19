package org.recruitmentservice.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.recruitmentservice.avatar.storage.AvatarProperties;
import org.recruitmentservice.dto.AvatarInfoDto;
import org.recruitmentservice.entity.AvatarInfo;
import org.recruitmentservice.entity.Employee;
import org.recruitmentservice.exception.NoSuchAvatarException;
import org.recruitmentservice.service.AvatarService;
import org.recruitmentservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@CrossOrigin
@RestController
@AllArgsConstructor
public class AvatarController {
    private final AvatarService avatarService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    private final AvatarProperties avatarProperties;

    @PostMapping("/avatar")
    @ResponseStatus(HttpStatus.CREATED)
    public AvatarInfoDto uploadAvatar(@RequestParam("file") MultipartFile file, @RequestHeader(value = "id") Long avatarId) throws IOException {
        AvatarInfo avatarInfo = new AvatarInfo();
        Employee employee = employeeService.getEmployeeById(2L);
        avatarInfo.setEmployee(employee);
        avatarInfo.setId(avatarId);
        validateLength(file);
        try (InputStream avatarBytes = file.getInputStream()) {
            return convertToDto(avatarService.save(avatarBytes, avatarInfo));
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/avatar", method = RequestMethod.GET)
    public byte[] getAvatar(@RequestHeader(value = "id") Long avatarId) {
        Long employeeId = avatarService.findById(avatarId).orElseThrow(() -> new NoSuchAvatarException(avatarId))
                .getEmployee().getEmployeeId();
        return avatarService.read(avatarId, employeeId);
    }

    private void validateLength(MultipartFile file) {
        if (file.getSize() > Long.parseLong(avatarProperties.getMaxMultipartSize())) {
            throw new IndexOutOfBoundsException("Avatar too large to upload");
        }
    }

    private AvatarInfoDto convertToDto(AvatarInfo avatarInfo) {
        return modelMapper.map(avatarInfo, AvatarInfoDto.class);
    }
}


