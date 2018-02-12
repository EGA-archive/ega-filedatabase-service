/* 
 * Copyright 2017 ELIXIR EGA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Author:  asenf
 * Created: 17-Feb-2017
 */

CREATE TABLE dev_ega_downloader.file (
	file_id  varchar(128) NULL,
	file_name varchar(256) NULL,
	file_size int8 NULL,
        checksum varchar(128) NULL,
        checksum_type varchar(12) NULL,
	file_status varchar(13) NULL
)
WITH (
	OIDS=FALSE
);
CREATE UNIQUE INDEX file_id_idx ON dev_ega_downloader.file (file_id);
