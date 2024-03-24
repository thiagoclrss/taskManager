#Gerenciador de Tarefas

Requesitos:
PostgreSQL com a respectiva url do banco de dado e dados do usuário do banco(usuário e senha) devidamente informadas no arquivo application.yml

Do funcionamento da aplicação:
  Rode a partir da classe TaskManagerApplication no pacote br.com.taskManager
  A aplicação pode ser utilizada pela interface abrindo o endpoint "localhost:3000/task/list".
  
  Os enpoints para acessar no navegador:
  
    "localhost:3000/task/list"
    Vai me retornar a aba principal da aplicação com toda a lista de tarefas. Nessa página é possível adicionar uma tarefa, editar e apagar uma tarefa desde que já exista. 
  
    "localhost:3000/task/form/add"
    Vai direcionar para o formulário para adicionar uma tarefa. Após clicar no botão de salvar tarefa o endpoint "localhost:3000/task/form/save" é chamado salvando a tarefa e redirecionando para a lista completa ou retornando para o form novamente caso os campos título e/ou validade estejam em branco.

    "localhost:3000/edit/{id}"
    Abre o form html retornando os dados da tarefa com ID passado na URL e me permite alterá-los.

    localhost:3000/delete/{id}
    Esse endpoint deleta a tarefa que tem o ID passado na URL.

  Dos testes:

    Para testar basta rodar cada método já criado na classe TaskControllerTest no pacote controller na pasta de test.
    
    
