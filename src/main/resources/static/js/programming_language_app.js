var programming_language_app = new Vue({
                el: '#programming-languages',
                data () {
                    return {
                        programmingLanguages: null,
                        programmingLanguageName: '',
                        editIndex: '',
                        programmingLanguageEdiId: '',
                        showModalEdit: false,
                        showModalAdd: false,
                        sortedAsc: false
                        
                    }
                },
                mounted () {
                    
                    this.getProgrammingLanguages();
                },
                methods : {
                    reflectEdit: function(editedItem){
                        this.programmingLanguages[this.editIndex].name = editedItem.name;
                        developer_app.getDevelopers();
                    },
                    getProgrammingLanguages: function(){
                        axios
                        .get('/api/programming-languages')
                        .then(response => {
                            this.programmingLanguages = response.data;
                            developer_app.programmingLanguageOptions = this.programmingLanguages;
                            search_app.programmingLanguageOptions = this.programmingLanguages;

                        })
                        .catch(function (error) {
                            console.log(error);
                          });
                    },
                    postProgrammingLanguage: function(){
                                            
                        this.showModalAdd = false;
                        axios.post('/api/programming-languages/add', {
                        name: this.programmingLanguageName
                      })
                      .then(function (response) {
                        console.log(response);
                        programming_language_app.programmingLanguageName = '';
                        programming_language_app.programmingLanguages.push(response.data);
                      })
                      .catch(function (error) {
                        console.log(error);
                      });
                    },
                    
                    putProgrammingLanguage: function(){
                        this.showModalEdit = false;
                        axios.put('/api/programming-languages/edit', {
                        id: this.programmingLanguageEditId,
                        name: this.programmingLanguageName
                      })
                      .then(function (response) {
                        console.log(response);
                        programming_language_app.programmingLanguageName = '';
                        programming_language_app.reflectEdit(response.data);
                      })
                      .catch(function (error) {
                        console.log(error);
                      });
                      
                    },
                    
                    deleteProgrammingLanguage: function(index){
                        var p = this.programmingLanguages[index];
                        axios.delete('/api/programming-languages/'+p.id);
                        
                        this.programmingLanguages.splice(index, 1);
                        developer_app.getDevelopers();
                    },
                    
                    showEditModal: function(index) {
                        var item = this.programmingLanguages[index];
                        this.editIndex = index;
                        this.programmingLanguageEditId = item.id;
                        this.programmingLanguageName = item.name;
                        this.showModalEdit = true;
                    },
                    showAddModal: function(){
                        this.showModalAdd = true;
                        
                    },
                    sort: function(){
                        this.programmingLanguages.sort(this.compare);
                        this.sortedAsc = !this.sortedAsc;
                    },
                    compare: function(a,b) {
                        //case insensitive comparison
                        var p = a.name.toUpperCase();
                        var q = b.name.toUpperCase();
                        if (p < q)
                          return this.sortedAsc?1:-1;
                        if (p > q)
                          return this.sortedAsc?-1:1;;
                        return 0;
                    }
                },
                
                
            });
