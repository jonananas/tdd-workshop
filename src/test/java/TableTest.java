

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TableTest {

	@Test
	public void testName() throws Exception {
		
		List<List<String>> table = new ArrayList<List<String>>();
		List<String> tableRow = Arrays.asList(new String[] {"one", "two", "three"});
		table.add(tableRow);
		table.add(tableRow);
		table.add(tableRow);

	}
	
	@Test
	public void shouldCreateTable() throws Exception {
		List<List<String>> table = createTable(1, 1);
		
		assertThat(table.get(0).get(0)).isEqualTo("row 0 col 0");
	}

	private List<List<String>> createTable(int rows, int cols) {
		List<List<String>> table = new ArrayList<List<String>>();
		for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
			ArrayList<String> row = new ArrayList<String>();
			for (int colIdx = 0; colIdx < cols; colIdx++) {
				row.add("row " + rowIdx + " col " + colIdx);
			}
			table.add(row);
		}
		return table;
	}
}
