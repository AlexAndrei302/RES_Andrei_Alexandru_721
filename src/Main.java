import controller.MissionControlController;
import repository.MissionRepository;
import service.MissionControlService;

public class Main {
    public static void main(String[] args) {
        // 1. Repository
        MissionRepository repository = new MissionRepository();

        // 2. Service
        MissionControlService service = new MissionControlService(repository);

        // 3. Controller
        MissionControlController controller = new MissionControlController(service);

        // 4. Run
        controller.run();
    }
}