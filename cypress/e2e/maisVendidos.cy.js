Cypress.on('uncaught:exception', (err, runnable) => {
    return false;
});

describe('Acessa Mais Vendidos na Amazon', () => {
    it('Abre o menu e acessa Mais Vendidos', () => {
        cy.visit('https://www.amazon.com.br/');

        cy.get('#nav-hamburger-menu').click();

        cy.contains('a.hmenu-item', 'Mais Vendidos').click();

        cy.get('#zg_banner_text').should('contain.text', 'Mais vendidos');

    });
});
