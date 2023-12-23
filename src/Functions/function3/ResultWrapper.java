package Functions.function3;

import java.util.List;

public class ResultWrapper<T> {
    private String continuation;
    private List<T> result;

    public List<T> getResult() {
        return result;
    }
}
