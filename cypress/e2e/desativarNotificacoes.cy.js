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

    cy.pause();

    //Vai para 'Sua Conta'
    cy.get('#nav-link-accountList', { timeout: 15000 }).click();

    //Vai para 'Preferências de Comunicação'
    cy.get('a[href*="/gp/cpc/homepage"]').contains('Preferências de comunicação').click();

    //Vai para 'Preferências de E-mail'
    cy.contains('Preferências de E-mail', { timeout: 10000 }).click();

    // Expande a seção "E-mails promocionais"
    cy.get('#marketingSettingsExpandImage', { timeout: 10000 })
        .should('be.visible')
        .click();

    // Espera a checkbox carregar e marca
    cy.contains('Não quero receber e-mails promocionais', { timeout: 10000 })
        .parents('label')
        .find('input[type="checkbox"]')
        .check({ force: true });

    // Clica no botão "Atualizar"
    cy.get('input[type="submit"][aria-labelledby*="update-marketing-preferences-button"]', { timeout: 10000 })
        .should('be.visible')
        .click();

    // Verifica se a mensagem de sucesso foi exibida
        cy.contains('Suas preferências foram atualizadas', { timeout: 10000 })
            .should('exist');
  });
});