package eu.elixir.ega.ebi.downloader.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import eu.elixir.ega.ebi.downloader.dto.DownloaderFile;
import eu.elixir.ega.ebi.downloader.service.FileService;

@RunWith(SpringRunner.class)
@WebMvcTest(DatasetController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class DatasetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileService fileService;

	@Test
	public void testGetFormats() throws Exception {
		final List<DownloaderFile> downloadList = new ArrayList<>();
		final DownloaderFile downloaderFile = new DownloaderFile();
		downloaderFile.setFileId("fileId");
		downloaderFile.setFileName("fileName");

		when(fileService.getDatasetFiles(any(String.class))).thenReturn(downloadList);
		final MockHttpServletResponse response = mockMvc
				.perform(get("/datasets/dataset_id/files").accept(APPLICATION_JSON)).andReturn().getResponse();
		assertThat(response.getStatus(), equalTo(OK.value()));
	}

}
