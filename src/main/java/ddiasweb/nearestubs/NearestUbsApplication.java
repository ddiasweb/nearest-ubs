package ddiasweb.nearestubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import ddiasweb.nearestubs.repo.UbsRepository;
import ddiasweb.nearestubs.util.CsvLoader;

import com.kakawait.spring.boot.picocli.autoconfigure.ExitStatus;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@SpringBootApplication
public class NearestUbsApplication {

	@Autowired
	UbsRepository repository;
		
	public static void main(String[] args)  {
        SpringApplication.run(NearestUbsApplication.class, args);
	}
	

	@Component
	@Command(name = "reload")
	class ReloadCommand extends HelpAwarePicocliCommand  {

	    @Option(names = {"-f", "--file"}, description = "ubs csv file")
	    String csvFile = "";

	    @Override
	    public ExitStatus call() {
            System.out.print("Reloading data...");
            repository.deleteAll();
            new CsvLoader(repository).load(csvFile);
            System.out.println(" Done!");
            System.exit(0);
            return ExitStatus.TERMINATION;
	    }
	}
	
}
