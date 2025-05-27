import random

def card_value(card):
    if card in ['J', 'Q', 'K']:
        return 10
    elif card == 'A':
        return 11
    else:
        return int(card)

def hand_score(cards):
    score = sum(card_value(c) for c in cards)
    ace_count = cards.count('A')
    while score > 21 and ace_count:
        score -= 10
        ace_count -= 1
    return score

def draw_card(deck):
    card = random.choice(deck)
    deck.remove(card)
    return card

def blackjack():
    deck = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'] * 4
    random.shuffle(deck)

    player = [draw_card(deck), draw_card(deck)]
    dealer = [draw_card(deck), draw_card(deck)]

    print(f"Your cards: {player} (Score: {hand_score(player)})")
    print(f"Dealer shows: {dealer[0]}")

    while hand_score(player) < 21:
        action = input("Do you want to 'hit' or 'stand'? ").lower()
        if action == 'hit':
            player.append(draw_card(deck))
            print(f"Your cards: {player} (Score: {hand_score(player)})")
        elif action == 'stand':
            break
        else:
            print("Invalid input.")

    if hand_score(player) > 21:
        print("You busted! Dealer wins.")
        return

    print(f"Dealer's cards: {dealer} (Score: {hand_score(dealer)})")
    while hand_score(dealer) < 17:
        dealer.append(draw_card(deck))
        print(f"Dealer draws... Cards: {dealer} (Score: {hand_score(dealer)})")

    player_score = hand_score(player)
    dealer_score = hand_score(dealer)

    if dealer_score > 21 or player_score > dealer_score:
        print("You win!")
    elif player_score < dealer_score:
        print("Dealer wins.")
    else:
        print("It's a tie!")

if __name__ == "__main__":
    blackjack()
