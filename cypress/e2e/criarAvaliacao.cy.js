describe('Comentários inválidos', () => {
    before(() => {
        cy.visit('https://www.amazon.com.br/');
        cy.get('#nav-link-accountList').click();
        cy.get('input[name="email"]').type('testesudesc@gmail.com');
        cy.get('#continue').click();
        cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
        cy.get('#signInSubmit').click();
        cy.get('#nav-link-accountList').should('contain.text', 'Olá');
    });

    it('conta sem compras', () => {
        cy.visit('https://www.amazon.com.br/');
        cy.get('#a-autoid-19-announce').click();

        cy.contains('Desculpe, você ainda não atende aos requisitos mínimos de qualificação para escrever uma avaliação na Amazon.').should('exists');
    });

    it('comentário sem estrelas', () => {

    });
});
