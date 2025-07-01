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

    //Vai para 'Sua Conta'
    cy.get('#nav-link-accountList', { timeout: 15000 }).click();

    //Vai para 'Preferências de Comunicação'
    cy.get('a[href*="/gp/cpc/homepage"]').contains('Preferências de comunicação').click();

    //Expande
    cy.get('#marketingSettingsExpandImage', { timeout: 10000 })
        .should('be.visible')
        .parent('a')
        .click();

    //Espera a checkbox carregar e marca
    cy.contains('span', 'Eletrônicos', { timeout: 10000 })
        .closest('label')
        .find('input[type="checkbox"]')
        .check({ force: true });

    //Clica em Atualizar
    cy.get('input[type="submit"][aria-labelledby="marketing-email-updateButton-announce"]', { timeout: 10000 })
        .should('be.visible')
        .click();

    //Verifica se a mensagem de sucesso foi exibida
    cy.get('#marketing-email-SuccessMessage', { timeout: 10000 })
        .should('be.visible')
        .and('contain', 'Suas configurações foram salvas.');

  });
});