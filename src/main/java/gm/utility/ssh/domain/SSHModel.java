package gm.utility.ssh.domain;

import lombok.Builder;

@Builder
public record SSHModel(String username, String ip, int port, String password) {
}
