// CT40 – Tentativa de finalizar compra sem método de pagamento válido
// Fluxo completo: login  ➜  adiciona 1 produto fixo ao carrinho  ➜  tenta
// fechar pedido  ➜  valida mensagem de erro sobre pagamento ausente/inválido.

describe('Compra inválida na Amazon (sem método de pagamento)', () => {
    it('Faz login, adiciona item ao carrinho e verifica erro de pagamento', () => {

        /* ---------- Login ---------- */
        cy.visit('https://www.amazon.com.br/');

        cy.get('#nav-link-accountList', { timeout: 15000 }).click();
        cy.get('input[name="email"]', { timeout: 10000 })
            .should('be.visible')
            .type('testesudesc@gmail.com');
        cy.get('#continue').click();

        cy.get('input[name="password"]', { timeout: 10000 })
            .should('be.visible')
            .type('TestesUDESC3!', { log: false });
        cy.get('#signInSubmit').click();

        // Confirma que está logado
        cy.get('#nav-link-accountList', { timeout: 15000 })
            .should('contain.text', 'Olá');

        /* ---------- Adiciona produto ---------- */
        // Abre diretamente o Headset Gamer (ASIN fixo)
        cy.visit('https://www.amazon.com.br/dp/B07Y2G7VX5');

        cy.get('#quantity', { timeout: 10000 }).select('1');
        cy.get('#add-to-cart-button', { timeout: 10000 }).click();

        // Mensagem de sucesso
        cy.get('#huc-v2-order-row-confirm-text', { timeout: 10000 })
            .should('contain.text', 'Adicionado ao carrinho');

        /* ---------- Checkout ---------- */
        cy.get('#nav-cart', { timeout: 15000 }).should('be.visible').click();
        cy.contains('Fechar pedido', { timeout: 15000 }).click();

        // Confirma endereço (caso o botão exista)
        cy.contains('Usar este endereço', { timeout: 15000 }).click();

        /* ---------- Validação de erro ---------- */
        cy.contains(/método de pagamento (válido|inválido|necessário)/i, { timeout: 15000 })
            .should('exist');

        // Opcional: pausa para inspeção visual quando precisar
        // cy.pause();
    });
});
