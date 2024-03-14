package gm.utility.ssh;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SSHType {
    EXEC("exec"),
    FILE("stfp");
    private final String value;

    public String value() {
        return this.value;
    }
}
