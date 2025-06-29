describe('Gerenciar notificações - Ativar e-mails promocionais', () => {

  it('Faz login e ativa os e-mails promocionais', () => {
    //Acessa a página inicial
    cy.visit('https://www.amazon.com.br');

    //Login
    cy.get('#nav-link-accountList', { timeout: 10000 }).click();
    cy.get('input[name="email"]').type('testesudesc@gmail.com');
    cy.get('#continue').click();
    cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
    cy.get('#signInSubmit').click();

    cy.pause();



  });
});