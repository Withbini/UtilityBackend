package gm.utility.sshUtil;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import gm.utility.sshUtil.domain.SshModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class SSHUtil {
    private SshModel model;

    private Session session;

    private ChannelExec channelExec;

    public void setModel(SshModel model) {
        this.model = model;
    }

    private void connect() throws JSchException {
        session = new JSch().getSession(model.username(), model.host(), model.port());
        session.setPassword(model.password());
        session.setConfig("StrictHostKeyChecking", "no");
        session.setConfig("UserKnownHostsFile", "/dev/null");
        session.connect();
    }

    public void command(String command) {
        try {
            connect();
            channelExec = (ChannelExec) session.openChannel("exec");    // 실행할 channel 생성

            channelExec.setCommand(command);
            channelExec.connect();

        } catch (JSchException e) {
            log.error("JSchException :{}", e);
        } finally {
            this.disconnect();
        }
    }

    public String getSSHResponse(String sourceDirectory, String command) {
        StringBuilder response = null;
        try {
            connect();
            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);

            InputStream inputStream = channelExec.getInputStream();
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channelExec.setOutputStream(responseStream);
            channelExec.connect();

            byte[] buffer = new byte[8192];
            int decodedLength;
            response = new StringBuilder();
            while ((decodedLength = inputStream.read(buffer, 0, buffer.length)) > 0)
                response.append(new String(buffer, 0, decodedLength));


        } catch (JSchException e) {
            log.error("JSchException exception:{}",e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.disconnect();
        }
        return response.toString();
    }

    private void disconnect() {
        if (session != null) session.disconnect();
        if (channelExec != null) channelExec.disconnect();
    }
}
