import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol {
    private String name;
    private String returnType;
    private List<Parameter> parameters;

    public FunctionSymbol(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void addParameter(String paramName, String paramType) {
        parameters.add(new Parameter(paramName, paramType));
    }
}