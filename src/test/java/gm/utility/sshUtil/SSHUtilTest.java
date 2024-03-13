package gm.utility.sshUtil;

import gm.utility.sshUtil.domain.SshModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SSHUtilTest {
    @InjectMocks
    SSHUtil sshUtil;

    @Test
    @DisplayName("연결요청후_데이터받기")
    void connect_test() {
        sshUtil.setModel(new SshModel("root", "127.0.0.1", 16677, "akaj124!"));
        //sshUtil.command("ls -al");
        sshUtil.getSSHResponse("", "top");
    }
}