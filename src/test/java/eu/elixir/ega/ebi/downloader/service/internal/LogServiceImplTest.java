package eu.elixir.ega.ebi.downloader.service.internal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import eu.elixir.ega.ebi.downloader.domain.entity.DownloadLog;
import eu.elixir.ega.ebi.downloader.domain.entity.Event;
import eu.elixir.ega.ebi.downloader.domain.repository.DownloadLogRepository;
import eu.elixir.ega.ebi.downloader.domain.repository.EventRepository;

@RunWith(SpringRunner.class)
public class LogServiceImplTest {

	@Autowired
	private LogServiceImpl logServiceImpl;

	@MockBean
	private DownloadLogRepository logRepository;

	@MockBean
	private EventRepository eventRepository;

	@Before
	public void setup() {
		when(eventRepository.save(any(Event.class))).thenReturn(getEvent());
		when(logRepository.save(any(DownloadLog.class))).thenReturn(getDownloadLog());
	}

	@Test
	public void testGetLogEvent() {
		assertThat(logServiceImpl.logEvent(getEvent()).getEvent(), equalTo(getEvent().getEvent()));
	}

	@Test
	public void testGetLogDownload() {
		assertThat(logServiceImpl.logDownload(getDownloadLog()).getFileId(), equalTo(getDownloadLog().getFileId()));
	}

	private Event getEvent() {
		final Event event = new Event();
		event.setEvent("event");
		return event;
	}

	private DownloadLog getDownloadLog() {
		final DownloadLog downloadLog = new DownloadLog();
		downloadLog.setFileId("fileId");
		return downloadLog;
	}

	@TestConfiguration
	static class FileServiceImplTestContextConfiguration {
		@Bean
		public LogServiceImpl fileService() {
			return new LogServiceImpl();
		}
	}

}
