describe('Manter cliente', () => {
    it('Cadastrar email válido', () => {
        cy.visit('https://www.amazon.com.br/');
        cy.get('#nav-link-accountList').click();
        //cy.get('.nav-signin-tooltip-footer').click();
        cy.get('input[name="email"]').type('testesamazon1234@gmail.com');
        cy.get('#continue').click();
        cy.contains('Parece que você é novo na Amazon').should('exist');
        cy.get('.a-button-input').click();
        cy.contains('Criar conta').should('exist');
    });
});