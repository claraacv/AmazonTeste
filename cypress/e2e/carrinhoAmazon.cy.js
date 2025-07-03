Cypress.on('uncaught:exception', (err, runnable) => {
    return false;
});

describe('Adicionar e remover item do carrinho da Amazon', () => {
    it('Adiciona um produto no carrinho, verifica e remove', () => {
        // Acessa o produto
        cy.visit('https://www.amazon.com.br/dp/6598392799');

        // Verifica se carregou o título do produto
        cy.get('#productTitle', { timeout: 10000 }).should('be.visible');

        cy.get('#add-to-cart-button').click();

        cy.get('h1.sw-atc-text').should('contain.text', 'Adicionado ao carrinho');

        // Vai para o carrinho para remover o item
        cy.visit('https://www.amazon.com.br/gp/cart/view.html');

        // Clica no ícone da lixeira para remover o item do carrinho
        cy.get('span[data-a-selector="decrement-icon"]').click();


        // Opcional: validar que o carrinho está vazio ou que o item foi removido
        cy.get('span.a-size-base')
            .contains('foi removido de Carrinho de compras')
            .should('be.visible');

    });
});
