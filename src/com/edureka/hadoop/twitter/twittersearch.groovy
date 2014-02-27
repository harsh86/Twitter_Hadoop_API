import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.GET
import groovy.json.JsonSlurper
import groovyx.net.http.*

def gettwitterresponse(url)
{
	def http = new RESTClient( 'https://api.twitter.com/1.1/search/tweets.json'+url )
	http.auth.oauth "xpU1UecFvHnj4OfGgXGA", "O2jrE3uF8xEXODpdIKCYIu0q9mBmJqBqtbu4f7zDzI", "129206280-oWFDkLfkvpgZfpIKe3L3ywIeFMHWaMHNimgDHgnw", "k700EB5p017VhEQnzrzDw6Jl0qKX0xQ7mM0Nqf3VAAPUz"
	def nexturl
	http.request(GET,TEXT) { req ->
		response.success = { resp, reader ->
			assert resp.status == 200
			println "My response handler got response: ${resp.statusLine}"
			println "Response length: ${resp.headers.'Content-Length'}"
			println "Response length: ${resp.headers.'Content-Type'}"
			def responsejson =  reader // print response reader
			def slurper = new JsonSlurper()
			def result = slurper.parse(responsejson)
			  nexturl =  result.search_metadata.next_results;
			  def jsonBuilder = new groovy.json.JsonBuilder()
			  jsonBuilder(result)
			 new File("/Users/harsh86/Dropbox/groovy/twitterresponse/response.json").append(result)
			 
			 
		}
	
		// called only for a 404 (not found) status code:
		response.'404' = { resp -> println 'Not found' }
	}
	return nexturl
}
def url 
for(int i =0;i<150;i++)
{
	if(url==null)
	{
		println "inside if----->"+url
		url ='?q=%23thankyousachin'
		url = gettwitterresponse(url)
	}
	else
	{
		println "inside else ---->"+url
		url = gettwitterresponse(url)
}
}



