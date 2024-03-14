package gm.utility.ssh.service;

import gm.utility.ssh.SSHType;
import gm.utility.ssh.domain.SSHModel;
import gm.utility.ssh.dto.SSHReqDto;
import gm.utility.ssh.dto.SSHResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SSHService {
    private final SSHUtil sshUtil;
    private final Encoder base64Encoder;

    public SSHResDto getSSHResult(SSHReqDto request) {
        String response = connectSSHAndGetData(request);
        return new SSHResDto(new String(base64Encoder.encode(response)), !response.isEmpty());
    }

    private String connectSSHAndGetData(SSHReqDto request) {
        SSHModel model = new SSHModel(request.username(), request.ip(), request.port(), request.password());
        return sshUtil.getSSHResponse(model, request.command(), SSHType.EXEC);
    }
}
