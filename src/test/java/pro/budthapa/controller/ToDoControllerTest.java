package pro.budthapa.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ToDoControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void verifyAllToDoList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/all").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}

	@Test
	public void verifyToDoById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.text").exists())
				.andExpect(jsonPath("$.completed").exists()).andExpect(jsonPath("$.id").value(2))
				.andExpect(jsonPath("$.text").value("Visit Kathmandu")).andExpect(jsonPath("$.completed").value(false))
				.andDo(print());

	}

	@Test
	public void verifyToDoByInvalidId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo/10").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(404)).andExpect(jsonPath("$.message").value("Todo not found"))
				.andDo(print());
	}

	@Test
	public void verifyToDoByInvalidSyntax() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo/sf545").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(400))
				.andExpect(jsonPath("$.message").value("Invalid request. Please check your syntax")).andDo(print());
	}

	@Test
	public void verifyToDoDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/todo/4").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(200)).andExpect(jsonPath("$.message").value("Todo deleted"))
				.andDo(print());
	}

	@Test
	public void verifyInvalidToDoDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/todo/500")).andExpect(jsonPath("$.errorCode").value(404))
				.andExpect(jsonPath("$.message").value("Unable to delete. Todo not found")).andDo(print());
	}

	@Test
	public void saveToDoTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/todo").contentType(MediaType.APPLICATION_JSON)
				.content("{\"text\": \"love budi\", \"completed\": true}")).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.text").exists()).andExpect(jsonPath("$.completed").exists())
				.andExpect(jsonPath("$.text").value("love budi")).andExpect(jsonPath("$.completed").value(true))
				.andDo(print());
	}

	@Test
	public void updateToDoTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/todo/5").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":5,\"text\":\"project completed\",\"completed\":true}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.text").exists()).andExpect(jsonPath("$.completed").exists())
				.andExpect(jsonPath("$.id").value(5)).andExpect(jsonPath("$.text").value("project completed"))
				.andExpect(jsonPath("$.completed").value(true)).andDo(print());
	}
}
