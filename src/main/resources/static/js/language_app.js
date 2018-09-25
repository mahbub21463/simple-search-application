var language_app = new Vue({
                el: '#languages',
                data () {
                    return {
                        languages: null,
                        languageCode: '',
                        editIndex: '',
                        languageEdiId: '',
                        showModalEdit: false,
                        showModalAdd: false,
                        sortedAsc: false
                    }
                },
                mounted () {
                   this.getLanguages();
                },
                methods : {
                    reflectEdit: function(editedItem){
                        this.languages[this.editIndex].code=editedItem.code;
                         developer_app.getDevelopers();
                    },
                    getLanguages: function(){
                        axios
                        .get('/api/languages')
                        .then(response => {
                            this.languages = response.data;
                            developer_app.languageOptions = this.languages;
                            search_app.languageOptions = this.languages;
                        })
                        .catch(function (error) {
                            console.log(error);
                          });
                    },
                    postLanguage: function(){
                        this.showModalAdd = false;
                        axios.post('/api/languages/add', {
                        code: this.languageCode
                      })
                      .then(function (response) {
                        console.log(response);
                        language_app.languageCode = '';
                        language_app.languages.push(response.data);
                      })
                      .catch(function (error) {
                        console.log(error);
                      });
                    },
                    putLanguage: function(){
                        this.showModalEdit = false;
                        axios.put('/api/languages/edit', {
                        id: this.languageEditId,
                        code: this.languageCode
                      })
                      .then(function (response) {
                        console.log(response);
                        language_app.languageCode = '';
                        language_app.reflectEdit(response.data);
                       
                      })
                      .catch(function (error) {
                        console.log(error);
                      });
                      
                    },
                    deleteLanguage: function(index){
                        var l = this.languages[index];
                        axios.delete('/api/languages/'+l.id)
                        .then(function (response) {
                            console.log(response);
                            if(response.status == 200){
                            language_app.languages.splice(index, 1);
                            developer_app.getDevelopers();
                        }
                       
                        })
                        .catch(function (error) {
                          console.log(error);
                        });
                        
                        
                    },
                    showEditModal: function(index) {
                        //console.log("index "+index);
                        var item = this.languages[index];
                        //console.log("item "+item);
                        this.editIndex = index;
                        this.languageEditId = item.id;
                        this.languageCode = item.code;
                        this.showModalEdit= true;
                    },
                    showAddModal: function(){
                        this.showModalAdd = true;
                    },
                    sort: function(){
                        this.languages.sort(this.compare);
                        this.sortedAsc = !this.sortedAsc;
                    },
                    compare: function(a,b) {
                        //case insensitive comparison
                        var p = a.code.toUpperCase();
                        var q = b.code.toUpperCase();
                        if (p < q)
                          return this.sortedAsc?1:-1;
                        if (p > q)
                          return this.sortedAsc?-1:1;;
                        return 0;
                    }
                },
                
            });