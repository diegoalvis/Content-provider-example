## Content Provider Sample

Example of content provider which demonstrates

* Making data available for other apps signed by the same certificate,
  as enforced by Android

* Making data available for apps with specific custom-defined
  permission

* Making data available for all apps without any limiting permissions

* Insert and Query DB via contentProvider insert and query.

* Query 'read-only' contentProvder which provides data from any-where
  (custom data).


The sample contains three flavor-variants. The main with the content
provider. A second signed by the same certificate which access the
content providers from the first. A third signed by different
certificate which fails to access the certificate signature based
content provider. Only the debug builds are signed with different
signatures.

### Other Samples

D:\Development\BnrSync\SamplesGithub\Android\2021\Content-provider-example\app\src\main\java\com\diegoalvis\exposedcontent\Constants.kt