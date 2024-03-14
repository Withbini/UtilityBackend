package gm.utility.ssh.service;

import gm.utility.ssh.SSHType;
import gm.utility.ssh.domain.SSHModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SSHUtil {
    public void execute(SSHModel model, String command, String type) {
        SSHConnect sshConnect = new SSHConnectImpl();
        sshConnect.connect(model);
        sshConnect.execute(command, type);
    }

    public String getSSHResponse(SSHModel model, String command, SSHType type) {
        SSHConnect sshConnect = new SSHConnectImpl();
        System.out.println(Thread.currentThread().getId()+"========================================");
        sshConnect.connect(model);
        sshConnect.execute(command, type.value());
        return sshConnect.getResponse();
    }
}
