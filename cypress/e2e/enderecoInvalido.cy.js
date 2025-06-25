describe('Cadastro de Endereço na Amazon - CEP inválido', () => {
  it('Faz login e tenta cadastrar endereço com CEP inválido', () => {
    //Acessa a página inicial
    cy.visit('https://www.amazon.com.br/');

    //Clica em "Conta e Listas"
    cy.get('#nav-link-accountList', { timeout: 15000 }).click();

    //Preenche o e-mail e continua
    cy.get('input[name="email"]', { timeout: 10000 }).should('be.visible').type('testesudesc@gmail.com');
    cy.get('#continue').click();

    //Preenche a senha e faz login
    cy.get('input[name="password"]', { timeout: 10000 }).should('be.visible').type('TestesUDESC3!', { log: false });
    cy.get('#signInSubmit').click();

    cy.pause();

    //Aguarda estar logado e vai para os endereços
    cy.get('#nav-link-accountList', { timeout: 15000 }).click();
    cy.contains('Seus endereços', { timeout: 10000 }).click();

    //Clica em "Adicionar endereço"
    cy.contains('Adicionar endereço', { timeout: 10000 }).click();

    //Preenche nome e CEP inválido
    cy.get('input[name="address-ui-widgets-enterAddressFullName"]').type('Thiago Schulze');
    cy.get('input[name="address-ui-widgets-enterAddressPostalCode"]').type('891500000');

    //Clica em "Salvar endereço"
    cy.get('#address-ui-widgets-form-submit-button input[type="submit"]')
        .should('be.visible')
        .click();

    cy.get('.a-alert-content .a-section')
        .should('be.visible')
        .and('contain.text', 'Insira');

  });
});