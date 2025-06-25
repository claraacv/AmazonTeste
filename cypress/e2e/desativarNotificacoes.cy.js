describe('Gerenciar notificações - Desativar e-mails promocionais', () => {
  it('Faz login e desativa os e-mails promocionais', () => {

    //Acessa a Amazon
    cy.visit('https://www.amazon.com.br/');

    //Login
    cy.get('#nav-link-accountList', { timeout: 15000 }).click();
    cy.get('input[name="email"]').should('be.visible').type('testesudesc@gmail.com');
    cy.get('#continue').click();
    cy.get('input[name="password"]').should('be.visible').type('TestesUDESC3!', { log: false });
    cy.get('#signInSubmit').click();

    //Vai para 'Sua Conta'
    cy.get('#nav-link-accountList', { timeout: 15000 }).click();
    cy.contains('Minha conta', { timeout: 10000 }).click();

    //Vai para 'Preferências de Comunicação'
    cy.contains('Preferências de Comunicação', { timeout: 10000 }).click();

    //Vai para 'Preferências de E-mail'
    cy.contains('Preferências de E-mail', { timeout: 10000 }).click();

    //Expande E-mails promocionais
    cy.contains('E-mails promocionais').click();

    //Espera carregar os departamentos
    cy.contains('Você está recebendo e-mails de', { timeout: 10000 }).should('exist');

    //Marca a checkbox "Não quero receber e-mails promocionais"
    cy.contains('Não quero receber e-mails promocionais')
        .parents('label') // Sobe para o <label> que envolve o texto e a checkbox
        .find('input[type="checkbox"]')
        .check({ force: true });

    //Clica em atualizar
    cy.contains('Atualizar').click();

    //Verifica se mensagem de sucesso foi exibida
    cy.contains('Suas preferências foram atualizadas').should('exist');
  });
});