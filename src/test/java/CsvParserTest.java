import com.ecsv.run.command.CommandFactory;
import com.ecsv.run.domain.City;
import com.ecsv.run.parser.CsvParser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static java.util.Objects.isNull;
import static junit.framework.TestCase.assertEquals;

public class CsvParserTest {

    File file;

    @Before
    public void setUp() {
        try {
            final URL resource = getClass().getClassLoader().getResource("cidades.csv");
            if (isNull(resource)) {
                throw new IllegalArgumentException("file not found.");
            }
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("file not found.");
        }
    }

    @Test
    public void shouldResult5565WhenExecuteCountAll() {
        List<City> cities = CsvParser.getInstance().cities(file);
        String input = "count *";
        Integer count = (Integer) new CommandFactory().getCommand(input).execute(cities);
        assertEquals(Integer.valueOf(5565), count);
    }

    @Test
    public void shouldResult27WhenExecuteCountDistinctUf() {
        List<City> cities = CsvParser.getInstance().cities(file);
        String input = "count distinct uf";
        Integer count = (Integer) new CommandFactory().getCommand(input).execute(cities);
        assertEquals(Integer.valueOf(27), count);
    }

    @Test
    public void shouldResult1WhenExecuteFilterNameManaus() {
        List<City> cities = CsvParser.getInstance().cities(file);
        String input = "filter name manaus";
        List<City> result = (List<City>) new CommandFactory().getCommand(input).execute(cities);
        assertEquals(1, result.size());
    }

    @Test
    public void shouldResult52WhenExecuteFilterUfRO() {
        List<City> cities = CsvParser.getInstance().cities(file);
        String input = "filter uf RO";
        List<City> result = (List<City>) new CommandFactory().getCommand(input).execute(cities);
        assertEquals(52, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldResultIllegalArgumentExceptWhenCommandPropertyIsInvalid() {
        List<City> cities = CsvParser.getInstance().cities(file);
        String input = "filter bairro RO";
        new CommandFactory().getCommand(input).execute(cities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldResultIllegalArgumentExceptWhenCommandIsEmpty() {
        List<City> cities = CsvParser.getInstance().cities(file);
        String input = "";
        new CommandFactory().getCommand(input).execute(cities);
    }
}
