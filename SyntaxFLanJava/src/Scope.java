import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Scope parentScope; // The enclosing scope
    private String scopeName; // The name of the scope (for debugging purposes)
    private Map<String, FunctionSymbol> functionSymbolTable; // Function symbol table
    private Map<String, VariableSymbol> variableSymbolTable; // Variable symbol table

    public Scope(Scope parentScope, String scopeName) {
        this.parentScope = parentScope;
        this.scopeName = scopeName;
        this.functionSymbolTable = new HashMap<>();
        this.variableSymbolTable = new HashMap<>();
    }

    // Getters and setters for parentScope, scopeName, and symbol tables

    public FunctionSymbol getFunction(String name) {
        FunctionSymbol function = functionSymbolTable.get(name);
        if (function != null) {
            return function;
        }
        // If not found in the current scope, check the parent scope
        if (parentScope != null) {
            return parentScope.getFunction(name);
        }
        return null; // Function not found in this or any parent scope
    }

    public VariableSymbol getVariable(String name) {
        VariableSymbol variable = variableSymbolTable.get(name);
        if (variable != null) {
            return variable;
        }
        // If not found in the current scope, check the parent scope
        if (parentScope != null) {
            return parentScope.getVariable(name);
        }
        return null; // Variable not found in this or any parent scope
    }

    public void addFunction(FunctionSymbol functionSymbol) {
        functionSymbolTable.put(functionSymbol.getName(), functionSymbol);
    }

    public void addVariable(VariableSymbol variableSymbol) {
        variableSymbolTable.put(variableSymbol.getName(), variableSymbol);
    }
}