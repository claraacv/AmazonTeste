Cypress.on('uncaught:exception', (err, runnable) => {
    return false;
});
describe('Fluxo de compra sem login exige autenticação', () => {
    it('Deve impedir checkout e pedir login', () => {
        // Acessa produto
        cy.visit('https://www.amazon.com.br/dp/6555872233');

        // Adiciona 1 unidade ao carrinho
        cy.get('#add-to-cart-button').click();

        // Verifica confirmação de adição
        cy.get('h1.sw-atc-text').should('contain.text', 'Adicionado ao carrinho');

        // Vai para o carrinho
        cy.get('#nav-cart').click();

        // Clica em "Fechar pedido" para avançar ao checkout
        cy.get('input[name="proceedToRetailCheckout"]', { timeout: 10000 }).should('be.visible').click();

        // Verifica que apareceu mensagem solicitando login ou redirecionou para a página de login
        cy.url().should('include', 'ap/signin');

        // Ou verifica mensagem na página de login que pede para autenticar
        cy.contains(/Faça login|Identifique-se|entre com sua conta/i).should('be.visible');
    });
});
