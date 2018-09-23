var search_app = new Vue({
                el: '#search',
                data () {
                    return {
                    selectedLanguages: [],
                    languageOptions: [],
                    selectedProgrammingLanguages: [],
                    programmingLanguageOptions: [],
                    
                    
                    }
                },
                
                methods: {
                    
                    
                    search: function(){
                        axios.post('/api/developers/search', {
                       
                        languages: this.selectedLanguages,
                        programmingLanguages: this.selectedProgrammingLanguages
                      })
                      .then(response => (developer_app.developers = response.data))
                      .catch(function (error) {
                        console.log(error);
                      });
                      
                    },
                   
                },
            });