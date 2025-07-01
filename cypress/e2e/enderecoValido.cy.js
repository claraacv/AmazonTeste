describe('Cadastro de Endereço na Amazon', () => {
  it('Faz login e cadastra um novo endereço com CEP válido', () => {

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
    
    //Aguarda estar logado e vai para os endereços
    cy.get('#nav-link-accountList', { timeout: 15000 }).click();
    cy.contains('Seus endereços', { timeout: 10000 }).click();

    //Clica em "Adicionar endereço"
    cy.contains('Adicionar endereço', { timeout: 10000 }).click();

    //Preenche nome e CEP
    cy.get('input[name="address-ui-widgets-enterAddressFullName"]').clear().type('Thiago Schulze');
    cy.get('input[name="address-ui-widgets-enterAddressPostalCode"]').type('89150000');

    cy.wait(3000);

    //Preenche o telefone
    cy.get('input[name="address-ui-widgets-enterAddressPhoneNumber"]')
        .should('be.visible')
        .type('47999998888');

    cy.wait(1000);

    //Preenche o endereço
    cy.get('input[name="address-ui-widgets-streetName"]', { timeout: 10000 })
        .should('be.visible')
        .type('Rua do Thiago');

    cy.wait(1000);

    //Preenche o número da residência
    cy.get('input[name="address-ui-widgets-buildingNumber"]')
        .should('be.visible')
        .type('123');

    cy.wait(1000);

    //Preenche o bairro
    cy.get('input[name="address-ui-widgets-neighborhood"]')
        .should('be.visible')
        .type('Centro');

    cy.wait(1000);

    //Clica em "Salvar endereço"
    cy.get('#address-ui-widgets-form-submit-button input[type="submit"]')
        .should('be.visible')
        .click();

    //Valida se o endereço foi salvo
    cy.contains('Endereço salvo', { timeout: 10000 }).should('exist');
  });
});