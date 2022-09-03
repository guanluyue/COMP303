import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {
    private final List<Command> aExecuted = new ArrayList<>();
    private final List<Command> aUndone = new ArrayList<>();
    private boolean prev_is_a_command = false;

    public void call(Command pCommand){
        pCommand.execute();
        aExecuted.add(pCommand);
        aUndone.clear();
        prev_is_a_command = true;
    }

    public void undo(){
        if (!aExecuted.isEmpty()) {
            Command command = aExecuted.remove(aExecuted.size() - 1);
            command.undo();
            aUndone.add(command);
            prev_is_a_command=false;
        }
    }

    public void redo(){
        Command command;
        if (!aUndone.isEmpty()){
            command = aUndone.remove(aUndone.size() - 1);
            command.execute();
            prev_is_a_command=false;
        }else {
            if(!aExecuted.isEmpty() && prev_is_a_command) {
                command = aExecuted.get(aExecuted.size() - 1);
                call(command);
            }
        }
    }

    public void clear(){
        this.aExecuted.clear();
        this.aUndone.clear();
        prev_is_a_command = false;
    }

}
