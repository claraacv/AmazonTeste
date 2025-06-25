describe('Cadastro de telefone para notificações SMS na Amazon', () => {
  it('Realiza login e configura preferências de SMS', () => {
    //Acessa a página inicial
    cy.visit('https://www.amazon.com.br');

    //Login
    cy.get('#nav-link-accountList', { timeout: 10000 }).click();
    cy.get('input[name="email"]').type('testesudesc@gmail.com');
    cy.get('#continue').click();
    cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
    cy.get('#signInSubmit').click();

    cy.pause();


    //Acessa o menu "Sua Conta"
    cy.get('#nav-link-accountList', { timeout: 10000 }).click();

    //Navega até "Preferências de Comunicação"
    cy.contains('Preferências de comunicação', { timeout: 10000 }).click();

    //Acessa seção "Preferências de SMS"
    cy.contains('Adicione seu número de telefone', { timeout: 10000 }).click();

    //Preenche número de telefone (simulado)
    cy.get('input[type="tel"]', { timeout: 10000 }).type('47999999999');

    //Marca as opções de SMS
    cy.contains('Atualizações de envio').parent().find('input[type="checkbox"]').check({ force: true });
    cy.contains('Problemas de pagamento').parent().find('input[type="checkbox"]').check({ force: true });

    //Clica em "Atualizar"
    cy.contains('Atualizar').click();

    //Verifica se a configuração foi salva com sucesso
    cy.contains('Suas preferências de SMS foram atualizadas', { timeout: 10000 }).should('exist');
  });
});