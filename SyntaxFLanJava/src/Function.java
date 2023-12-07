import java.util.List;

public class Function {
    private String name;
    private List<String> parameters;
    private List<FLanguageParser.ElementContext> body;

    public Function(String name, List<String> parameters, List<FLanguageParser.ElementContext> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public List<FLanguageParser.ElementContext> getBody() {
        return body;
    }
}