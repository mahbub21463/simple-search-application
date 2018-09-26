var search_app = new Vue({
                el: '#search',
                data () {
                    return {
                    language: '',
                    programmingLanguage: '',
                    
                    
                    
                    }
                },
                
                methods: {
                    
                    
                    search: function(){
                        var params = new URLSearchParams();
                        var languages = this.language.split(",");
                        
                        for(var i=0;i<languages.length;i++){
                           
                            params.append("language", languages[i].trim());
                        }
                        var programmingLanguages = this.programmingLanguage.split(",");
                        for(var i=0;i<programmingLanguages.length;i++){
                            
                            params.append("programmingLanguage", programmingLanguages[i].trim());
                        }
                       
                        var request = {
                          params: params
                        };
                        
                       axios.get('/api/developers/search', request)
                      .then(response => (developer_app.developers = response.data))
                      .catch(function (error) {
                        console.log(error);
                      });
                      
                    },
                    searchWhoWriteRuby: function(){
                        axios.get('/api/developers/search', {
                            params: {
                                programmingLanguage: 'ruby'
                            }
                        })
                      .then(response => (developer_app.developers = response.data))
                      .catch(function (error) {
                        console.log(error);
                      });
                    },
                    searchWhoWriteRubySpeakJapanese: function(){
                        axios.get('/api/developers/search', {
                            params: {
                                language: 'ja',
                                programmingLanguage: 'ruby'
                            }
                        })
                      .then(response => (developer_app.developers = response.data))
                      .catch(function (error) {
                        console.log(error);
                      });
                    },
                    searchWhoWriteRubyAndJavascriptSpeakJapanese: function(){
                        var params = new URLSearchParams();
                        params.append("programmingLanguage", 'ruby');
                        params.append("programmingLanguage", 'JavaScript');
                        params.append("language", 'ja');
                        var request = {
                          params: params
                        };
                       axios.get('/api/developers/search-who-write-two-programming-language-speak-one-language', request)
                      .then(response => (developer_app.developers = response.data))
                      .catch(function (error) {
                        console.log(error);
                      });
                    },
                    searchNotUsedProgrammingLanguages: function(){
                        axios.get('/api/programming-languages/search-not-used-programming-languages')
                      .then(response => (programming_language_app.programmingLanguages = response.data))
                      .catch(function (error) {
                        console.log(error);
                      });
                    }
                   
                },
            });