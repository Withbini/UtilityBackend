package gm.utility.ssh.controller;

import gm.utility.global.response.APIResponse;
import gm.utility.ssh.dto.SSHReqDto;
import gm.utility.ssh.dto.SSHResDto;
import gm.utility.ssh.service.SSHService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class sshController {
    private final SSHService service;

    @PostMapping("/ssh")
    public ResponseEntity<APIResponse<SSHResDto>> connectSSHAndGetData(@RequestBody @Validated SSHReqDto request, BindingResult bindingResult) {
        return APIResponse.createPostResponse(service.getSSHResult(request));
    }
}
