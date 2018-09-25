var developer_app = new Vue({
                el: '#developers',
                data () {
                    return {
                    developers: null,
                    selectedLanguages: [],
                    languageOptions: [],
                    selectedProgrammingLanguages: [],
                    programmingLanguageOptions: [],
                    developerEmail: '',
                    editIndex: '',
                    developerEdiId: '',
                    showModalAdd: false,
                    showModalEdit: false,
                    sortedAsc: false
                    
                    }
                },
                mounted () {
                    this.getDevelopers();
                },
                methods: {
                    
                    reflectEdit: function(editedItem){
                        this.developers[this.editIndex].email=editedItem.email;
                        this.developers[this.editIndex].languages=editedItem.languages;
                        this.developers[this.editIndex].programmingLanguages=editedItem.programmingLanguages;
                    },
                    languageListToString: function(languageList) {
                        var result = languageList.map(language => language.code);
                        return result.join(", ");
                    },
                    programmingLanguageListToString: function(progrmmingLanguageList) {
                        var result = progrmmingLanguageList.map(programmingLanguage => programmingLanguage.name);
                        return result.join(", ");
                    },
                    deleteDeveloper: function(index){
                        var d = this.developers[index];
                        axios.delete('/api/developers/'+d.id)
                        .then(function (response) {
                            console.log(response);
                            if(response.status == 200){
                            developer_app.developers.splice(index, 1);
                        }
                            
                       
                        })
                        .catch(function (error) {
                          console.log(error);
                        });
                    },
                    getDevelopers: function(){
                        axios
                        .get('/api/developers')
                        .then(response => (this.developers = response.data))
                        .catch(function (error) {
                            console.log(error);
                        });
                    },
                    postDeveloper: function(){
                        this.showModalAdd = false;
                        axios.post('/api/developers/add', {
                        email: this.developerEmail,
                        languages: this.selectedLanguages,
                        programmingLanguages: this.selectedProgrammingLanguages
                      })
                      .then(function (response) {
                        console.log(response);
                        developer_app.developerEmail = '';
                        developer_app.developers.push(response.data);
                      })
                      .catch(function (error) {
                        console.log(error);
                      });
                    },
                    putDeveloper: function(){
                        this.showModalEdit = false;
                        axios.put('/api/developers/edit', {
                        id: this.developerEditId,
                        email: this.developerEmail,
                        languages: this.selectedLanguages,
                        programmingLanguages: this.selectedProgrammingLanguages
                      })
                      .then(function (response) {
                        console.log(response);
                        developer_app.developerEmail = '';
                        developer_app.reflectEdit(response.data);
                        
                      })
                      .catch(function (error) {
                        console.log(error);
                      });
                      
                    },
                    showEditModal: function(index) {
                        //console.log("index "+index);
                        var item = this.developers[index];
                        //console.log("item "+item);
                        this.editIndex = index;
                        this.developerEditId = item.id;
                        this.developerEmail = item.email;
                        this.selectedLanguages = item.languages;
                        this.selectedProgrammingLanguages = item.programmingLanguages;
                        this.showModalEdit= true;
                    },
                    showAddModal: function() {
                      
                        this.showModalAdd= true;
                    },
                    sort: function(){
                        this.developers.sort(this.compare);
                        this.sortedAsc = !this.sortedAsc;
                    },
                    compare: function(a,b) {
                        //case insensitive comparison
                        var p = a.email.toUpperCase();
                        var q = b.email.toUpperCase();
                        if (p < q)
                          return this.sortedAsc?1:-1;
                        if (p > q)
                          return this.sortedAsc?-1:1;;
                        return 0;
                    }
                },
            });