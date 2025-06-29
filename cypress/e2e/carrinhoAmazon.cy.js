describe('Adicionar e remover produto no carrinho - Amazon ASIN fixo', () => {
    before(() => {
        cy.visit('https://www.amazon.com.br/');
        cy.get('#nav-link-accountList').click();
        cy.get('input[name="email"]').type('testesudesc@gmail.com');
        cy.get('#continue').click();
        cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
        cy.get('#signInSubmit').click();
        cy.get('#nav-link-accountList').should('contain.text', 'Olá');
    });

    it('Adicionar 2 unidades do produto ao carrinho', () => {
        // Acessa diretamente a página do produto pelo ASIN
        cy.visit('https://www.amazon.com.br/dp/B07Y2G7VX5');

        // Seleciona quantidade 2
        cy.get('#quantity', { timeout: 10000 }).select('2');

        // Clica no botão "Adicionar ao carrinho"
        cy.get('#add-to-cart-button', { timeout: 10000 }).click();

        // Confirma mensagem de sucesso
        cy.get('#huc-v2-order-row-confirm-text', { timeout: 10000 })
            .should('contain.text', 'Adicionado ao carrinho');

        // Vai para o carrinho
        cy.get('#nav-cart').should('be.visible').click();

        // Verifica se o produto está no carrinho com quantidade 2
        cy.get('.sc-list-item-content').within(() => {
            cy.contains('Headset Gamer').should('exist');
            cy.get('span.a-dropdown-prompt').should('contain.text', '2');
        });

        // Verifica que o subtotal está visível
        cy.get('#sc-subtotal-amount-buybox').should('exist');
    });

    it('Remover 1 unidade do carrinho e verificar total', () => {
        cy.get('#nav-cart').click();

        cy.get('.sc-list-item-content').within(() => {
            cy.get('select.a-dropdown-prompt').select('1');
        });

        cy.wait(3000);

        cy.get('.sc-list-item-content').within(() => {
            cy.get('span.a-dropdown-prompt').should('contain.text', '1');
        });

        cy.get('#sc-subtotal-amount-buybox').should('exist');
    });
});
